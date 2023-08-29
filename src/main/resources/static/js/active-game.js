"use strict";


function alphabetListener() {
    document.addEventListener("DOMContentLoaded", function () {
        console.log("Testing");
        const clickableDivs = document.querySelectorAll(".alphabet-char");

        for (const value of clickableDivs.values()) {
            console.log(value);
        }

        const inputField = document.getElementById("form-input-character");
        clickableDivs.forEach(function (div) {
            div.addEventListener("click", function () {
                const content = div.textContent;
                console.log("Clicked div content:", content);
                inputField.value = content;
            });
        });

        /*const copyButton = document.getElementById("copyButton");

        copyButton.addEventListener("click", function () {
            inputField.value = sourceDiv.innerText;
        });*/
    })

}

alphabetListener();


/*document.addEventListener("DOMContentLoaded", function () {
    const clickableDivs = document.querySelectorAll(".clickable-div");

    clickableDivs.forEach(function (div) {
        div.addEventListener("click", function () {
            const content = div.textContent;
            console.log("Clicked div content:", content);
        });
    });
});*/

