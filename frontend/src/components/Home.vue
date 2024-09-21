<script setup>
    import axios from 'axios';
    import Carousel from './homepage_content/Carousel.vue';

    import { ref, onMounted } from 'vue';
    const ans=ref([]);

    onMounted(async ()=>{
        const str=await axios.get("http://localhost:8080/")
        ans.value=str.data;
    })
</script>

<template>
    <p>{{ans}}</p>
    <main>
        <Carousel />
    </main>
    
</template>