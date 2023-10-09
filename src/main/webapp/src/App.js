import './App.scss';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Admin from './components/Admin';
import Signup from './components/Signup';
import Home from './components/Home';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home />}></Route>
        <Route path='/signup' element={<Signup />}></Route>
        <Route path='/admin' element={<Admin />}></Route>
      </Routes>
    </BrowserRouter>
    // <div className='app-container'>
    //   <Header />
    //   <Login />
    // </div>
  );
}

export default App;
