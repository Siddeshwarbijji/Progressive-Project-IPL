import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AuthService } from "../../services/auth.service";

@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent  {
    
    // fullName: string = '';
    // username: string = '';
    // email: string = '';
    // password: string = '';
    successMessage: string = '';
    errorMessage: string = '';

    registrationForm: FormGroup;

    constructor(private fb: FormBuilder,private authService: AuthService){
        this.registrationForm = this.fb.group({
            fullName: ['',[Validators.required,Validators.pattern(/^[a-zA-Z ]+$/)]],
            username:['',[Validators.required,Validators.pattern(/^[a-zA-Z0-9]+$/)]],
            email: ['',[Validators.required,Validators.email]],
            password: ['',[Validators.required,Validators.pattern(/^(?=.*[A-Z])(?=.*\d).{8,}$/)]]
        });
        
    }

    ngOnInit(): void{

    }

    get f(){
        return this.registrationForm.controls;
    }

    onSubmit(): void{
        // this.successMessage = '';
        // this.errorMessage = '';
        if(this.registrationForm.valid){
            this.authService.createUser(this.registrationForm.value).subscribe(
                response => {
                    this.successMessage = 'Registration successful!';
                    this.errorMessage = '';
                    this.resetForm();
                }
            )
            console.log(this.registrationForm.value);
            
        }else{
            
            this.errorMessage = 'Please fill out all required fields correctly.';
            //this.errorMessage = '';
            this.successMessage = '';
            
        }
    }

    resetForm(){
        this.registrationForm.reset();
        // this.successMessage = '';
        // this.errorMessage = '';
    }
}
