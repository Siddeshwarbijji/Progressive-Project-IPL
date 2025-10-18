import { Component } from "@angular/core";
import { Cricketer } from "../../types/Cricketer";

@Component({
    selector: 'app-cricketerarray',
    templateUrl: './cricketerarray.component.html',
    styleUrls: ['./cricketerarray.component.scss'],
    standalone: false
})
export class CricketerArrayComponent  {

    cricketers: Cricketer[] = [];

    ngOnInit(){
        this.cricketers.push(new Cricketer(1,1,'Virat Kohli',35,'Indian',17,"Batsman",35000,25));
        this.cricketers.push(new Cricketer(2,1,'AB de Villiers',37,'South Africa',15,"Batsman",28000,17));
        this.cricketers.push(new Cricketer(3,1,'Chris Gayle',37,'West Indies',20,"Batsman",25000,30));
        this.cricketers.push(new Cricketer(4,2,'Abhishek',23,'Indian',5,"Batsman",5000,25));
    }

    showCricketers:boolean = false;

    toggleCricketers(){
        this.showCricketers = !this.showCricketers;
    }
}
