new Vue({
    el: "#app",
    data: {
        departments: [],
        editMode: false,
        errorMessage: "",
        apiUrl: "http://localhost:8080/api/departments",
        form: {deptName: "", building: "", budget: null},
    },
    methods: {
        async apiRequest(method, url, data = null) {
            try {
                const response = await axios({ method, url, data });
                this.errorMessage = "";
                return response.data;
            } catch (error) {
                this.errorMessage = error.response?.data?.message || "请求失败：未知错误";
                console.error(error);
                throw error;
            }
        },
        handleRequest(method, url, data = null) {
            this.apiRequest(method, url, data)
                .then((data) => {
                    if (method === "get") {
                        this.departments = data;
                    } else {
                        this.fetchDepartments();
                        this.resetForm();
                    }
                });
        },
        fetchDepartments() {
            const url = this.apiUrl;
            this.handleRequest("get", url);
        },
        createDepartment() {
            const url = this.apiUrl;
            this.handleRequest("post", url, this.form);
        },
        updateDepartment() {
            const url = `${this.apiUrl}/${this.form.id}`;
            this.handleRequest("put", url, this.form);
        },
        deleteDepartment(id) {
            const url = `${this.apiUrl}/${id}`;
            this.handleRequest("delete", url);
        },
        saveDepartment() {
            if (this.editMode) {
                this.updateDepartment(this.form.id)
            } else {
                this.createDepartment();
            }
        },
        editDepartment(department) {
            this.form = { ...department };
            this.editMode = true;
        },
        resetForm() {
            this.editMode = false;
            this.errorMessage = "";
            this.form = {deptName: "", building: "", budget: null};
        },
    },
    mounted() {
        this.fetchDepartments();
    },
});