/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const img = document.getElementById('id-portada');
const hidden = document.getElementById('hidden');
const imgBtn = document.getElementById('portada-pelicula');

const Cloudinary = 'https://api.cloudinary.com/v1_1/djsa7v6bs/image/upload';

const idCloudinary = 'p9deemh4';

imgBtn.addEventListener('change', async (e) => {
    const file = e.target.files[0];

    const formData = new FormData();
    formData.append('file', file);
    formData.append('upload_preset', idCloudinary)

    const res = await axios.post(Cloudinary, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
    hidden.value = res.data.secure_url;
    img.src = res.data.secure_url;
});

