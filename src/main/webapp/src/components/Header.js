import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

const Header = (props) => {
    return (<>
        <Navbar expand="lg" className="bg-body-tertiary">
            <Container>
                <Navbar.Brand href="admin"></Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="/home">Trang chủ</Nav.Link>
                        <Nav.Link href="#link">Link</Nav.Link>
                        <NavDropdown title="Tuỳ chọn" id="basic-nav-dropdown">
                            <NavDropdown.Item href="#action/3.1">Đổi mật khẩu</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="#action/3.4">
                                Đăng xuất
                            </NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                    <div className='welcome'>
                        Xin chào,
                        <img src='..\src\Assets\Picture\profileavt.png' />
                    </div>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    </>)
}

export default Header;