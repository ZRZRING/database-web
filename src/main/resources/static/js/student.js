new Vue({
    el: "#app",
    data: {
        students: [],
        editMode: false,
        errorMessage: "",
        form: {id: null, name: "", deptName: "", totalCredits: null},
    },
    methods: {
        fetchStudent() {
            axios
                .get("http://localhost:8080/api/students")
                .then((response) => {
                    this.students = response.data;
                    this.errorMessage = "";
                })
                .catch((error) => {
                    this.errorMessage = "无法获取学生列表，请检查后端服务";
                    console.error(error);
                });
        },
        modifyStudent() {
            axios
                .put(`http://localhost:8080/api/students/${this.form.id}`, this.form)
                .then(() => {
                    this.fetchStudent();
                    this.resetForm();
                    this.errorMessage = "";
                })
                .catch((error) => {
                    this.errorMessage = "无法保存学生信息，请检查后端服务";
                    console.error(error);
                });
        },
        insertStudent() {
            axios
                .post("http://localhost:8080/api/students", this.form)
                .then(() => {
                    this.fetchStudent();
                    this.resetForm();
                    this.errorMessage = "";
                })
                .catch((error) => {
                    this.errorMessage = "无法添加学生，请检查后端服务";
                    console.error(error);
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
            this.form = {...student};
            this.editMode = true;
        },
        deleteStudent(id) {
            axios
                .delete(`http://localhost:8080/api/students/${id}`)
                .then(() => {
                    this.fetchStudent();
                    this.errorMessage = "";
                })
                .catch((error) => {
                    this.errorMessage = "无法删除学生，请检查后端服务";
                    console.error(error);
                });
        },
        resetForm() {
            this.editMode = false;
            this.errorMessage = "";
            this.form = {id: null, name: "", deptName: "", totalCredits: null};
        },
    },
    mounted() {
        this.fetchStudent();
    },
});