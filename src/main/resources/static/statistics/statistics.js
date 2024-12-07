new Vue({
    el: '#app',
    data: {
        url: 'http://localhost:8080/api/statistics/failures',
        errorMessage: '',
        failureData: [],
    },
    methods: {
        async fetchFailureData() {
            try {
                const response = await axios.get(this.url);
                this.failureData = response.data;
                this.renderChart();
            } catch (error) {
                this.errorMessage = "数据加载失败，请稍后重试！";
                console.error(error);
            }
        },
        renderChart() {
            const chartDom = document.getElementById('gradeChart');
            const myChart = echarts.init(chartDom);
            const option = {
                title: {
                    text: '不及格人数统计',
                    left: 'center',
                    top: 20,
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow',
                    },
                },
                xAxis: {
                    type: 'category',
                    data: this.failureData.map(item => item.courseTitle),
                    axisLabel: {
                        interval: 0,
                        rotate: 45,
                    },
                },
                yAxis: {
                    type: 'value',
                    name: '人数',
                },
                series: [{
                    name: '不及格人数',
                    type: 'bar',
                    data: this.failureData.map(item => item.count),
                    itemStyle: {
                        color: '#FF5733',
                    },
                }],
            };
            myChart.setOption(option);
        }
    },
    mounted() {
        this.fetchFailureData();
    }
});
