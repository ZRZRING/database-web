new Vue({
    el: "#app",
    data: {
        courses: [],
        editMode: false,
        errorMessage: "",
        apiUrl: "http://localhost:8080/api/courses",
        form: { id: null, title: "", deptName: "", credits: null },
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
                        this.courses = data;
                    } else {
                        this.fetchCourses();
                        this.resetForm();
                    }
                });
        },
        fetchCourses() {
            const url = this.apiUrl;
            this.handleRequest("get", url);
        },
        createCourse() {
            const url = this.apiUrl;
            this.handleRequest("post", url, this.form);
        },
        updateCourse() {
            const url = `${this.apiUrl}/${this.form.id}`;
            this.handleRequest("put", url, this.form);
        },
        deleteCourse(id) {
            const url = `${this.apiUrl}/${id}`;
            this.handleRequest("delete", url);
        },
        saveCourse() {
            if (this.editMode) {
                this.updateCourse(this.form.id)
            } else {
                this.createCourse();
            }
        },
        editCourse(course) {
            this.form = { ...course };
            this.editMode = true;
        },
        resetForm() {
            this.editMode = false;
            this.errorMessage = "";
            this.form = { id: null, title: "", deptName: "", credits: null };
        },
    },
    mounted() {
        this.fetchCourses();
    },
});
