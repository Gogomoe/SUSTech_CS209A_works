<template>
    <main id="m-main-page">
        <div class="m-keyword-area">

            <div class="m-keyword" v-for="k of keywords" v-bind:class="{ 'v-fade-in': k.fadeIn,'v-hide': k.hide }">
                <div class="u-keyword">{{k.keyword}}</div>
                <div class="u-detail">{{k.detail}}</div>
            </div>

        </div>
    </main>
</template>

<script>
    export default {
        name: "MainPage",
        data() {
            return {
                keywords: [],
            }
        },
        mounted() {
            this.loadKeywords()
        },
        methods: {
            getKeywords: function () {
                return [
                    {keyword: "TEXT QWQ", detail: "25 条商品信息"},
                    {keyword: "TEXT QWQ", detail: "25 条商品信息"},
                    {keyword: "TEXT QWQ", detail: "25 条商品信息"},
                    {keyword: "TEXT QWQ", detail: "25 条商品信息"},
                    {keyword: "TEXT QWQ", detail: "25 条商品信息"},
                    {keyword: "TEXT QWQ", detail: "25 条商品信息"}
                ]
            },
            loadKeywords: function () {
                return new Promise((resolve, reject) => {
                    let ks = this.getKeywords();
                    ks.forEach(it => {
                        it.hide = true
                    });
                    this.keywords.push(...ks);

                    for (let i = 0; i < ks.length; i++) {
                        setTimeout(() => {
                            ks[i].hide = false;
                            ks[i].fideIn = true;
                        }, i * 100);
                    }
                    setTimeout(resolve, ks.length * 100 + 300);
                });
            }
        }
    }
</script>

<style scoped>

</style>