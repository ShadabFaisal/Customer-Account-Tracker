
class AuthService{

    checkIfUserIsPresent() {
        const user = JSON.parse(localStorage.getItem("userDetails"));
        return user;
    }
}
export default new AuthService();