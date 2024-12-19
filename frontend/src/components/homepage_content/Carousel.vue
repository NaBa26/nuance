<template>
    <div id="carouselExampleAutoplaying" class="carousel slide banner-carousel justify-content-center" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="banner-image-wrapper">
                    <img src="/public/assets/images/bg_images/31286.jpg" class="d-block w-60 banner-image" alt="Custom">
                </div>
            </div>
            <div class="carousel-item">
                <div class="banner-image-wrapper">
                    <img src="/public/assets/images/bg_images/gift_packages.jpg" class="d-block w-60 banner-image" alt="George RR Martin">
                </div>
            </div>
            <div class="carousel-item">
                <div class="banner-image-wrapper">
                    <img src="/public/assets/images/bg_images/well_known.jpg" class="d-block w-60 banner-image" alt="Best Seller">
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.banner-carousel {
    height: 800px;
    overflow: hidden;
    padding: 2%;
}

.banner-image-wrapper {
    position: relative;
    height: 100%;
    width: 100%;
}

.banner-image {
    object-fit: cover;
    height: 100%;
    width: 100%;
}

.banner-image-wrapper::after {
    content: "";
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 45%;
    background: linear-gradient(to top, rgba(255, 255, 255, 100), rgba(255, 255, 255, 0));
}

.carousel-inner {
    height: 100%;
}
</style>
