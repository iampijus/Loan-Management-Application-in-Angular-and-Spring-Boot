import { Component, OnInit } from '@angular/core';
import borrowersData from '../../../../assets/api/borrowers.json';
import { ColDef } from 'ag-grid-community';
import { UserServicesService } from 'src/app/services/user-services/user-services.service';

@Component({
  selector: 'app-user-status',
  templateUrl: './user-status.component.html',
  styleUrls: ['./user-status.component.css'],
})
export class UserStatusComponent implements OnInit {
  data: any[] = borrowersData;
  loans: any[];
  email: string;
  constructor(private userServicesService: UserServicesService) {}

  ngOnInit(): void {
    this.getLoanStatus();
  }

  getLoanStatus() {
    let user = sessionStorage.getItem('user');

    if (user) {
      this.email = JSON.parse(user).email;
    }

    this.userServicesService
      .getLoanStatusByEmail(this.email)
      .subscribe((data) => {
        console.log(data);
        this.loans = data;
      });
  }

  colDefs: ColDef[] = [
    {
      headerName: '#',
      valueGetter: 'node.rowIndex+1',
      headerClass: 'header-cell',
    },
    {
      headerName: 'Type',
      field: 'loanDetails.type',
      filter: 'agTextColumnFilter',
    },
    {
      headerName: 'Amount',
      field: 'loanDetails.amount',
      filter: 'agNumberColumnFilter',
      valueFormatter: (params) => `₹ ${params.value}`,
    },
    { headerName: 'Credit Score', field: 'score', valueGetter:params=>{
      if(params.data.score===0){
        return "Score pending";
      }else{
        return params.data.score;
      }
    } },
    {
      headerName: 'Status',
      field: 'status',
      cellStyle: (params) => {
        if (params.value === 'Pending') {
          return { color: '#FFAA00' };
        } else if (params.value === 'Approved') {
          return { color: 'green' };
        } else if (params.value === 'Rejected') {
          return { color: 'red' };
        }
        return null;
      },
    },
  ];

  defaultColDef = {
    flex: 1,
    minWidth: 100,
    cellStyle: { 'font-size': '16px' },
    headerClass: 'my-header-class',
  };
}
