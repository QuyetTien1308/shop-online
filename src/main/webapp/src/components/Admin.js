import React from 'react';
import { Container } from 'react-bootstrap';
import AHeader from './AHeader';
import ASidebar from './ASidebar';
import ATable from './ATable';

const Admin = (props) => {
    return (
        <div className='app-container'>
            <Container>
                <AHeader />
                <div className='row'>
                    <div className='col-sm-2'>
                        <ASidebar />
                    </div>
                    <div className='col-sm-10'>
                        <ATable />
                    </div>
                </div>
            </Container>
        </div >
    )
}
export default Admin;