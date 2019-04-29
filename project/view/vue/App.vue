<template>
    <div id="g-body">

        <div id="g-top-container">
            <component v-bind:is="topBar"></component>
        </div>
        <div id="g-main-container" class="g-page-container">
            <component
                    v-bind:is="currentPage" v-on:toggle-page="togglePage($event.page)"
            ></component>
        </div>

    </div>
</template>

<script>
    import MainPage from './MainPage.vue';

    export default {
        name: "App",
        data() {
            return {
                currentPage: 'mainPage',
                topBar: 'topJumbotron'
            }
        },
        computed: {},
        components: {
            'mainPage': MainPage,
            'topJumbotron': null,
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