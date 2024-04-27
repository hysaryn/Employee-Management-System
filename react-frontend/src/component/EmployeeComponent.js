import React, { Component } from 'react'
import EmployeeService from '../service/EmployeeService';

export default class EmployeeComponent extends Component {
  constructor(props) {
    super(props);
    
    this.state = {
      employee: {},
      department: {},
      organization: {}
    }
  }

  componentDidMount() {
    EmployeeService.getEmployee().then((res) => {
        this.setState({ employee: res.data.employee });
        this.setState({ department: res.data.department });
        this.setState({ organization: res.data.organization });

        console.log(this.state.employee);
        console.log(this.state.department);
        console.log(this.state.organization);
    });
  }
  
  render() {
    return (
      <div>
        <div className="card col-mid-6 offset-md-3">
            <h3 className="text-center card-header">View Employee Details</h3>
            <div className='card-body'>
                <div className="row">
                    <p>Employee First Name: {this.state.employee.firstName}</p>
                </div>
                <div className="row">
                    <p>Employee Last Name: {this.state.employee.lastName}</p>
                </div>
                <div className="row">
                    <p>Employee Email: {this.state.employee.email}</p>
                </div>
            </div>

            <h3 className="text-center card-header">View Department Details</h3>
            <div className='card-body'>
                <div className="row">
                    <p>Department Name: {this.state.department.departmentame}</p>
                </div>
                <div className="row">
                    <p>Department Description: {this.state.department.departmentDescription}</p>
                </div>
                <div className="row">
                    <p>Department Code: {this.state.department.departmentCode}</p>
                </div>
            </div>

            <h3 className="text-center card-header">View Organization Details</h3>
            <div className='card-body'>
                <div className="row">
                    <p>Organization Name: {this.state.organization.organizationName}</p>
                </div>
                <div className="row">
                    <p>Organization Description: {this.state.organization.organizationDescription}</p>
                </div>
                <div className="row">
                    <p>Organization Code: {this.state.organization.organizationCode}</p>
                </div>
            </div>
        </div>

      </div>
    )
  }
}
