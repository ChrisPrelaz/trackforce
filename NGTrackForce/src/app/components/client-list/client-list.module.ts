import { NgModule } from '@angular/core';

import { RequestService } from '../../services/request-service/request.service';
import { ClientService } from '../../services/client-service/client.service';
import { ClientListComponent} from './client-list.component';

import {ClientListRoutingModule } from './client-list-routing.module';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { SearchFilterPipeModule } from '../../pipes/search-filter/search-filter.module';
@NgModule({
    declarations: [
        ClientListComponent
    ],
    providers:[
        RequestService,
        ClientService
    ],
    imports:[
        ClientListRoutingModule,
        CommonModule,
        FormsModule,
        ChartsModule,
      MatProgressSpinnerModule,
      SearchFilterPipeModule
    ]
})
export class ClientListModule { }
