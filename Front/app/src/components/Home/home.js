import React from "react";

const Home = () => {
  return (
    <div
    className='p-5 text-center bg-image'
    style={{ backgroundImage: "url('https://www.capita.com/sites/g/files/nginej291/files/styles/webp_with_original_image/public/2022-11/My%20project%20-%202022-11-25T115705.261.jpg.webp?itok=wjzrALg9')", backgroundize: "cover" }}
  >
    <div className='mask' style={{ backgroundColor: 'rgba(0, 0, 0, 0.6)'}}>
      <div className='d-flex justify-content-center align-items-center h-100'>
        <div className='text-white' style={{ padding: 32}}>
          <h1 className='mb-3'>Sistema de gestion de reclamos</h1>
          <h4 className='mb-3'>Te ayudamos a resolver los problemas que tenés</h4>
        </div>
      </div>
    </div>
  </div>
  );
};

export default Home;
