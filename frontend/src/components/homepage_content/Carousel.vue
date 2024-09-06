<template>
    <div id="carouselExampleIndicators" class="carousel slide d-flex justify-content-center align-items-center" style="height: 100vh;">
        <!-- Carousel Indicators -->
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3"
                aria-label="Slide 4"></button>
        </div>

        <!-- Carousel Inner -->
        <div class="carousel-inner" style="width: 50%; height: 50%;">
            <div class="carousel-item active">
                <img src="/public/assets/images/first_time_buyers.jpg" class="d-block w-100" alt="first_time_buyer">
            </div>
            <div class="carousel-item">
                <img src="/public/assets/images/best_seller.jpg" class="d-block w-100" alt="best_seller">
            </div>
            <div class="carousel-item">
                <img src="/public/assets/images/george_rr_martin.jpg" class="d-block w-100" alt="event">
            </div>
            <div class="carousel-item">
                <img src="/public/assets/images/custom.jpg" class="d-block w-100" alt="gift_package">
            </div>
        </div>

        <!-- Carousel Controls -->
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true" style="background-color: rgba(0, 0, 0, 0.5);"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true" style="background-color: rgba(0, 0, 0, 0.5);"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</template>
