const thumbnail = document.querySelector('#file');
const image = document.querySelector('.img-responsive')
thumbnail.addEventListener('change', function(event) {
    const file = event.target.files[0]
    image.src = URL.createObjectURL(file)
})