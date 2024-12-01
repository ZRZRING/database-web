new Vue({
    el: "#app",
    data: {
        instructors: [],
        editMode: false,
        errorMessage: "",
        apiUrl: "http://localhost:8080/api/instructors",
        form: { id: null, name: "", deptName: "", salary: null },
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
                        this.instructors = data;
                    } else {
                        this.fetchInstructors();
                        this.resetForm();
                    }
                });
        },
        fetchInstructors() {
            const url = this.apiUrl;
            this.handleRequest("get", url);
        },
        createInstructor() {
            const url = this.apiUrl;
            this.handleRequest("post", url, this.form);
        },
        updateInstructor() {
            const url = `${this.apiUrl}/${this.form.id}`;
            this.handleRequest("put", url, this.form);
        },
        deleteInstructor(id) {
            const url = `${this.apiUrl}/${id}`;
            this.handleRequest("delete", url);
        },
        saveInstructor() {
            if (this.editMode) {
                this.updateInstructor(this.form.id)
            } else {
                this.createInstructor();
            }
        },
        editInstructor(instructor) {
            this.form = { ...instructor };
            this.editMode = true;
        },
        resetForm() {
            this.editMode = false;
            this.errorMessage = "";
            this.form = { id: null, name: "", deptName: "", salary: null };
        },
    },
    mounted() {
        this.fetchInstructors();
    },
});
