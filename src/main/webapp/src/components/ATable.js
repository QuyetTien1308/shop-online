import { useState, useRef } from 'react';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

const ATable = (props) => {
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [images, setImages] = useState([]);
    const [isDragging, setIsDragging] = useState(false);
    const fileInputRef = useRef(null);

    function selectFiles() {
        fileInputRef.current.click();
    }
    function onFileSelect(event) {
        const files = event.target.files;
        if (files.length === 0) return;
        for (let i = 0; i < files.length; i++) {
            if (files[i].type.split('/')[0] !== 'image') continue;
            if (!images.some((e) => e.name === files[i].name)) {
                setImages((prevImages) => [
                    ...prevImages,
                    {
                        name: files[i].name,
                        url: URL.createObjectURL(files[i]),
                    },
                ]);
            }
        }
    }
    function deleteImage(index) {
        setImages((prevImages) =>
            prevImages.filter((_, i) => i !== index)
        )
    }
    function onDragOver(event) {
        event.preventDefault();
        setIsDragging(true);
        event.dataTransfer.dropEffect = "copy";
    }
    function onDragLeave(event) {
        event.preventDefault();
        setIsDragging(false);
    }
    function onDrop(event) {
        event.preventDefault();
        setIsDragging(false);
        const files = event.dataTransfer.files;
        for (let i = 0; i < files.length; i++) {
            if (files[i].type.split('/')[0] !== 'image') continue;
            if (!images.some((e) => e.name === files[i].name)) {
                setImages((prevImages) => [
                    ...prevImages,
                    {
                        name: files[i].name,
                        url: URL.createObjectURL(files[i]),
                    },
                ]);
            }
        }
    }
    return (
        <>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Số thứ tự</th>
                        <th>Tiêu đề</th>
                        <th>Hình ảnh</th>
                        <th>Trạng thái</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Mark</td>
                        <td className='pic-table'>
                            <img src='https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/2023/10/banner/Desk-1-380x200.png'
                                width={200} />
                        </td>
                        <td>Otto</td>
                        <td>cc</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                </tbody>
            </Table>
            <div>
                <Button variant="dark" size="lg" onClick={handleShow}>
                    Thêm mới
                </Button>

                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Thêm Slider</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div>
                            <label className='form-label'>Tiêu đề</label>
                            <input type='text' className='form-control'></input>
                            <label className='form-label'>Hình ảnh</label>
                            <div className='card'>
                                <div className='top'>
                                    <div className='drag-area' onDragOver={onDragOver} onDragLeave={onDragLeave} onDrop={onDrop}>
                                        {isDragging ? (
                                            <span className='select'>Thả hình ảnh vào đây</span>
                                        ) : (
                                            <>
                                                Kéo thả hình ảnh vào đây hoặc {""}
                                                <span className='select' role='button' onClick={selectFiles}>
                                                    tải tệp lên
                                                </span>
                                            </>
                                        )}
                                        <input name='file'
                                            type='file'
                                            className='file'
                                            ref={fileInputRef}
                                            onChange={onFileSelect}>
                                        </input>
                                    </div>
                                    <div className='container'>
                                        {images.map((images, index) => (
                                            <div className='image' key={index}>
                                                <span className='delete' onClick={() => deleteImage(index)}>&times;</span>
                                                <img src={images.url} alt={images.name}></img>
                                            </div>
                                        ))}
                                        <img src='' alt='' />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Đóng
                        </Button>
                        <Button variant="dark" onClick={handleClose}>
                            Lưu
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>
        </>
    );
}

export default ATable;