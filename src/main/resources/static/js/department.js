new Vue({
    el: "#app",
    data: {
        departments: [],
        editMode: false,
        errorMessage: "",
        form: {deptName: "", building: "", budget: null},
    },
    methods: {
        fetchDepartment() {
            axios
                .get("http://localhost:8080/api/departments")
                .then((response) => {
                    this.departments = response.data;
                    this.errorMessage = "";
                })
                .catch((error) => {
                    this.errorMessage = "无法获取院系列表，请检查后端服务";
                    console.error(error);
                });
        },
        modifyDepartment() {
            axios
                .put(`http://localhost:8080/api/departments/${this.form.deptName}`, this.form)
                .then(() => {
                    this.fetchDepartment();
                    this.resetForm();
                    this.errorMessage = "";
                })
                .catch((error) => {
                    this.errorMessage = "无法保存院系信息，请检查后端服务";
                    console.error(error);
                });
        },
        insertDepartment() {
            axios
                .post("http://localhost:8080/api/departments", this.form)
                .then(() => {
                    this.fetchDepartment();
                    this.resetForm();
                    this.errorMessage = "";
                })
                .catch((error) => {
                    this.errorMessage = "无法添加院系，请检查后端服务";
                    console.error(error);
                });
        },
        saveDepartment() {
            if (this.editMode) {
                this.modifyDepartment();
            } else {
                this.insertDepartment();
            }
        },
        editDepartment(department) {
            this.form = {...department};
            this.editMode = true;
        },
        deleteDepartment(deptName) {
            axios
                .delete(`http://localhost:8080/api/departments/${deptName}`)
                .then(() => {
                    this.fetchDepartments();
                    this.errorMessage = "";
                })
                .catch((error) => {
                    this.errorMessage = "无法删除院系，请检查后端服务";
                    console.error(error);
                });
        },
        resetForm() {
            this.editMode = false;
            this.errorMessage = "";
            this.form = {deptName: "", building: "", budget: null};
        },
    },
    mounted() {
        this.fetchDepartment();
    },
});