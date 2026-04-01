let isSignUp = false;
const signUpHolder = document.getElementById("signUpHolder");
const switchBtn = document.getElementById("switchBtn");
const signInDiv = document.getElementById("signInHolder");
const signUpForm = document.getElementById("signUpForm");

function switchForm() {
    if (!isSignUp) {
        signUpHolder.style.width = "100%";
        signUpHolder.style.borderRadius = "100px";
        signInDiv.style.opacity = "0";
        switchBtn.value = "Sign In";
        signUpForm.style.display = "block";
        isSignUp = true;
    } else {
        signUpHolder.style.width = "45%";
        signInDiv.style.opacity = "1";
        switchBtn.value = "Sign Up";
        signUpForm.style.display = "none";
        isSignUp = false;
    }
}