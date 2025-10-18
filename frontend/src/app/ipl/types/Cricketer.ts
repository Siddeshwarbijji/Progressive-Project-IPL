import { Team } from "./Team";

export class Cricketer {
  cricketerId:number;
  // teamId:number;
  cricketerName:string;
  age:number;
  nationality: string;
  experience: number;
  role: string;
  totalRuns: number;
  totalWickets: number;
  team: Team;

  constructor(cricketerId:number,cricketerName:string, age:number,nationality: string, experience: number, role: string, totalRuns: number, totalWicketes: number,team:Team){
    this.cricketerId = cricketerId;
    // this.teamId = team.teamId;
    this.cricketerName = cricketerName;
    this.age = age;
    this.nationality = nationality;
    this.experience = experience;
    this.role = role;
    this.totalRuns = totalRuns;
    this.totalWickets = totalWicketes;
    this.team = team;
  }

  displayInfo():void{
    console.log(`Cricketer ID: ${this.cricketerId}`);//, Team ID: ${this.teamId}, Name: ${this.cricketerName}`);
    console.log(`Team ID: ${this.team.teamId}`);
    console.log(`Cricketer Name: ${this.cricketerName}`);
  }
   
}





