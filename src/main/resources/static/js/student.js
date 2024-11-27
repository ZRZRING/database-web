new Vue({
    el: "#app",
    data: {
        students: [],
        editMode: false,
        errorMessage: "",
        form: {id: null, name: "", deptName: "", totalCredits: null},
    },
    methods: {
        apiRequest(method, url, data = null) {
            const config = {
                method: method,
                url: url,
                data: data,
            };
            return axios(config)
                .then((response) => {
                    this.errorMessage = "";
                    return response.data;
                })
                .catch((error) => {
                    if (error.response && error.response.data && error.response.data.message) {
                        this.errorMessage = `请求失败: ${error.response.data.message}`;
                    } else {
                        this.errorMessage = "请求失败: 请检查后端服务";
                    }
                    console.error(error);
                    throw error;
                });
        },
        fetchStudent() {
            this.apiRequest("get", "http://localhost:8080/api/students")
                .then((data) => {
                    this.students = data;
                });
        },
        modifyStudent() {
            this.apiRequest("put", `http://localhost:8080/api/students/${this.form.id}`, this.form)
                .then(() => {
                    this.fetchStudent();
                    this.resetForm();
                });
        },
        insertStudent() {
            this.apiRequest("post", "http://localhost:8080/api/students", this.form)
                .then(() => {
                    this.fetchStudent();
                    this.resetForm();
                });
        },
        saveStudent() {
            if (this.editMode) {
                this.modifyStudent();
            } else {
                this.insertStudent();
            }
        },
        editStudent(student) {
            this.form = { ...student };
            this.editMode = true;
        },
        deleteStudent(id) {
            this.apiRequest("delete", `http://localhost:8080/api/students/${id}`)
                .then(() => {
                    this.fetchStudent();
                    this.errorMessage = "";
                });
        },
        resetForm() {
            this.editMode = false;
            this.form = { id: null, name: "", deptName: "", totalCredits: null };
        },
    },
    mounted() {
        this.fetchStudent();
    },
});
