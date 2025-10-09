import { Component } from "@angular/core";
import { Team } from "../../types/Team";

@Component({
    selector: 'app-teamsample',
    templateUrl: './teamsample.component.html',
    styleUrls: ['./teamsample.component.scss'],
    standalone: true
})
export class TeamSampleComponent  {

    team: Team = new Team(1,"CSK","Chennai","Kavya",2011);
  
}
