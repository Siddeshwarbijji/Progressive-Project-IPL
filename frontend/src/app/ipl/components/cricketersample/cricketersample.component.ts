import { Component } from "@angular/core";
import { Cricketer } from "../../types/Cricketer";

@Component({
    selector: 'app-cricketersample',
    templateUrl: './cricketersample.component.html',
    styleUrls: ['./cricketersample.component.scss'],
    standalone: false
})
export class CricketerSampleComponent {
  cricketer: Cricketer = new Cricketer(1,1,"Virat",35,"Indian",17,"Batsman",18000,25);
}
