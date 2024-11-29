new Vue({
    el: "#app",
    data: {
        students: [],
        editMode: false,
        errorMessage: "",
        apiUrl: "http://localhost:8080/api/students",
        form: { id: null, name: "", deptName: "", totalCredits: null },
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
                        this.students = data;
                    } else {
                        this.fetchStudents();
                        this.resetForm();
                    }
                });
        },
        fetchStudents() {
            const url = this.apiUrl;
            this.handleRequest("get", url);
        },
        createStudent() {
            const url = this.apiUrl;
            this.handleRequest("post", url, this.form);
        },
        updateStudent() {
            const url = `${this.apiUrl}/${this.form.id}`;
            this.handleRequest("put", url, this.form);
        },
        deleteStudent(id) {
            const url = `${this.apiUrl}/${id}`;
            this.handleRequest("delete", url);
        },
        saveStudent() {
            if (this.editMode) {
                this.updateStudent(this.form.id)
            } else {
                this.createStudent();
            }
        },
        editStudent(student) {
            this.form = { ...student };
            this.editMode = true;
        },
        resetForm() {
            this.editMode = false;
            this.errorMessage = "";
            this.form = { id: null, name: "", deptName: "", totalCredits: null };
        },
    },
    mounted() {
        this.fetchStudents();
    },
});
