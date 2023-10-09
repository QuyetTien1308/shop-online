import ListGroup from 'react-bootstrap/ListGroup';

function ASidebar() {
    const alertClicked = () => {
        alert('You clicked the third ListGroupItem');
    };

    return (
        <ListGroup defaultActiveKey="#link1">
            <ListGroup.Item action href="#slider" variant='dark'>
                Quản lý Slider
            </ListGroup.Item>
            <ListGroup.Item action href="#link2" variant='dark'>
                Quản lý sản phẩm
            </ListGroup.Item>
            <ListGroup.Item action href="#link3" variant='dark' disabled>
                Link 3
            </ListGroup.Item>
            <ListGroup.Item action href="#link4" variant='dark' disabled>
                Link 4
            </ListGroup.Item>
            <ListGroup.Item action href="#link5" variant='dark' disabled>
                Link 5
            </ListGroup.Item>
            <ListGroup.Item action onClick={alertClicked}>
                This one is a button
            </ListGroup.Item>
        </ListGroup>
    );
}

export default ASidebar;