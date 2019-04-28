<template>
    <component
            v-bind:is="currentPage" v-on:toggle-page="togglePage($event.page)"
    ></component>
</template>

<script>
    import MainPage from './MainPage.vue';

    export default {
        name: "App",
        data() {
            return {
                currentPage: 'mainPage'
            }
        },
        computed: {},
        components: {
            'mainPage': MainPage,
        },
        methods: {
            togglePage: function (page) {
                this.currentPage = page;
            }
        }
    }
</script>

<style lang="scss" scoped>
</style>