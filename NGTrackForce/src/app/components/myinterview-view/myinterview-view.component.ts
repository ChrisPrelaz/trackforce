import { Component, OnInit } from '@angular/core';
import { AssociateService } from '../../services/associate-service/associate.service';
import { AutoUnsubscribe } from '../../decorators/auto-unsubscribe.decorator';
import { Associate } from '../../models/associate.model';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from '../../services/client-service/client.service';
/**
*@author Katherine Obioha
*
*@description This is the view for associates only
*
*/
@Component({
  selector: 'app-myinterview-view',
  templateUrl: './myinterview-view.component.html',
  styleUrls: ['./myinterview-view.component.css']
})
@AutoUnsubscribe
export class MyInterviewComponent implements OnInit {
  public interviews: Array<any> = [];
  public associate: Associate = new Associate();
  public id:number = 0;
  public newInterview: any = {
    client: null,
    date: null,
    type: null,
    feedback: null
  }
  public formOpen: boolean = false;

  constructor(
    private associateService: AssociateService,
    private activated: ActivatedRoute) { }

  ngOnInit() {
    //gets the associate id from the path
    //the '+' coerces the parameter into a number
    this.id = +this.activated.snapshot.paramMap.get('id');
    this.getInterviews(this.id);
    this.getAssociate(this.id);
  }



  toggleForm() {
    this.formOpen = !this.formOpen;
  }


  addInterview(){
    console.log(this.newInterview);
    let interview = {
      associateId: this.id,
      clientId: this.newInterview.client,
      typeId: this.newInterview.type,
      interviewDate: new Date(this.newInterview.date).getTime(),
      interviewFeedback: this.newInterview.feedback
    };
    this.associateService.addInterviewForAssociate(this.id,interview).subscribe(
      data => {
        this.getInterviews(this.id);
      },
      err => {
        console.log(err);
      }
    )

  }

  getInterviews(id: number) {
    this.associateService.getInterviewsForAssociate(id).subscribe(
      data => {
        let tempArr = [];
        for (let i=0;i<data.length;i++) {
          let interview = data[i];
          let intObj = {
            id: interview.id,
            client: interview.tfClientName,
            date: new Date(interview.tfInterviewDate),
            type: interview.typeName,
            feedback: interview.tfInterviewFeedback
          }
          tempArr.push(intObj);
        }
        this.interviews = tempArr;
      }
    )
  }
  
  
    getAssociate(id: number){
    this.associateService.getAssociate(id).subscribe(
      data => {
        this.associate = data;
      },
      err => {
        console.log(err);
    });
  }
}
