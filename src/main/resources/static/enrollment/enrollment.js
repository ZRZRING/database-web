new Vue({
    el: "#app",
    data: {
        enrollments: [],
        errorMessage: "",
        apiUrl: "http://localhost:8080/api/enrollments",
        form: { id: null, studentName: "", courseTitle: "", grade: null },
    },
    methods: {
        async apiRequest(method, url, data = null) {
            try {
                const response = await axios({ method, url, data });
                this.errorMessage = "";
                return response.data;
            } catch (error) {
                this.errorMessage = error.response?.data?.message || "请求失败: 未知错误";
                console.error(error);
                throw error;
            }
        },
        handleRequest(method, url, data = null) {
            this.apiRequest(method, url, data)
                .then((data) => {
                    if (method === "get") {
                        this.enrollments = data;
                    } else {
                        this.fetchEnrollments();
                        this.resetForm();
                    }
                });
        },
        fetchEnrollments() {
            const url = this.apiUrl;
            this.handleRequest("get", url);
        },
        createEnrollment() {
            const url = this.apiUrl;
            this.handleRequest("post", url, this.form);
        },
        deleteEnrollment(id) {
            const url = `${this.apiUrl}/${id}`;
            this.handleRequest("delete", url);
        },
        saveEnrollment() {
            this.createEnrollment();
        },
        resetForm() {
            this.editMode = false;
            this.errorMessage = "";
            this.form = { id: null, studentName: "", courseTitle: "", grade: null };
        },
    },
    mounted() {
        this.fetchEnrollments();
    },
});
