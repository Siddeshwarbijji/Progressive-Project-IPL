import { Component } from "@angular/core";

import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { Cricketer } from "../../types/Cricketer";
import { Team } from "../../types/Team";
import { IplService } from "../../services/ipl.service";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
    selector: 'app-cricketercreate',
    templateUrl: './cricketercreate.component.html',
    styleUrls: ['./cricketercreate.component.scss'],

})
export class CricketerCreateComponent {

    cricketer: Cricketer | null = null;
    successMessage: string = '';
    errorMessage: string='';
    teams: Team[] = [];
    cricketerForm!: FormGroup;

    isSubmitted: boolean = false;

    constructor(private fb: FormBuilder,private iplService: IplService){}

    ngOnInit(){
        this.loadTeams();
        this.cricketerForm = this.fb.group({
            //cricketerId: ['',[Validators.required]],
            teamId: ['',[Validators.required]],
            cricketerName: ['',[Validators.required]],
            age: ['',[Validators.required,Validators.min(18)]],
            nationality: ['',[Validators.required]],
            experience: ['',[Validators.required,Validators.min(0)]],
            role: ['',[Validators.required]],
            totalRuns: ['',[Validators.required,Validators.min(0)]],
            totalWickets: ['',[Validators.required,Validators.min(0)]]
        });
    }

    loadTeams(): void{
        this.iplService.getAllTeams().subscribe((teams) => {
            this.teams = teams;
        });
    }

    get cricketerId(){
        return this.cricketerForm.get('cricketerId');
    }

    get teamId(){
        return this.cricketerForm.get('teamId');
    }

    get cricketerName(){
        return this.cricketerForm.get('cricketerName');
    }

    get age(){
        return this.cricketerForm.get('age');
    }

    get nationality(){
        return this.cricketerForm.get('nationality');
    }

    get experience(){
        return this.cricketerForm.get('experience');
    }

    get role(){
        return this.cricketerForm.get('role');
    }

    get totalRuns(){
        return this.cricketerForm.get('totalRuns');
    }

    get totalWickets(){
        return this.cricketerForm.get('totalWickets');
    }

    onSubmit(){
        this.isSubmitted = true;
        if(this.cricketerForm.valid){
            this.iplService.addCricketer(this.cricketerForm.value).subscribe({
                next: (response) => {
                    this.cricketer = response;
                    this.errorMessage = '';
                    console.log(this.cricketer);
                    this.cricketerForm.reset();
                },
                error: (error) => {
                    this.handleError(error);
                },
                complete: () => {
                    this.successMessage = 'Cricketer create successfully!';
                }
            })
            // this.cricketer = {...this.cricketerForm.value};
            // this.successMessage = 'Cricketer created successfully!'
            // this.errorMessage = '';
        }
        else{
            this.successMessage = '';
            this.errorMessage = 'Please fill out all required fields correctly.';
        }
    }

    private handleError(error: HttpErrorResponse): void{
        if(error.error instanceof ErrorEvent){
            this.errorMessage = `Client-side error: ${error.error.message}`;
        }
        else{
            this.errorMessage = `Server-side error: ${error.status} ${error.message}`;
            if(error.status === 400){
                this.errorMessage = 'Bad request. Please check your input.';
            }
        }
        this.successMessage = '';
    }

    resetForm(){
        this.cricketerForm.reset({
            cricketerName:'',
            age:18,
            nationality:'',
            experience:'',
            role:'',
            totalRuns:0,
            totalWickets:0
        
        });

        this.isSubmitted = false;
        this.successMessage = '';
        this.errorMessage = '';
    }

    // simulateBackendError(){
    //     return !!this.cricketer.cricketerName
    // }
}