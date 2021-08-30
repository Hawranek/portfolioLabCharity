document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {

            // TODO: Validation
            this.validation();
            this.$step.innerText = this.currentStep;

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            // TODO: get data from inputs and show them in summary
            this.summary();
        }

        /**
         * Summary of donation
         */
        summary() {
            const summary = document.querySelector(".summary");
            const topsum1 = summary.querySelector("li > .icon-bag").nextSibling.nextSibling;
            const topsum2 = summary.querySelector(".icon-hand").parentElement.querySelector(".summary--text");
            const bottomsumcol1 = summary.querySelector(".form-section--columns").firstChild.nextSibling;
            const bottomsumcol2 = summary.querySelector(".form-section--columns").lastChild.previousSibling;
            const quantityinput = document.querySelector("input#quantity");
            const categoriesinput = document.querySelectorAll("input[name='categories']");
            const institutioninput = document.querySelectorAll("input[name='institution']");
            let street = "";
            let city = "";
            let zipCode = "";
            let pickUpDate = "";
            let pickUpTime = "";
            let pickUpComment = "";
            if (this.currentStep == 5) {
                const quantity = quantityinput.value;
                let categories = "";
                for (let i = 0; i < categoriesinput.length; i++) {
                    if (categoriesinput[i].checked) {
                        if (i !== 0) {
                            categories += ", ";
                        }
                        categories += categoriesinput[i].parentElement.querySelector(".description").innerText;
                    }
                }
                let institution = "";
                for (let i = 0; i < institutioninput.length; i++) {
                    if (institutioninput[i].checked) {
                        institution = institutioninput[i].parentElement.querySelector(".description").innerText;
                    }
                }
                topsum1.innerText = quantity + " worków zawierających " + categories;
                topsum2.innerText += " " + institution;

                street = document.querySelector("input#street").value;
                city = document.querySelector("input#city").value;
                zipCode = document.querySelector("input#zipCode").value;
                pickUpDate = document.querySelector("input#pickUpDate").value;
                pickUpTime = document.querySelector("input#pickUpTime").value;
                pickUpComment = document.querySelector("textarea#pickUpComment").value;
                const listreet = document.createElement("li");
                listreet.innerText = street;
                const licity = document.createElement("li");
                licity.innerText = city;
                const lizipCode = document.createElement("li");
                lizipCode.innerText = zipCode;
                bottomsumcol1.querySelector("ul").appendChild(listreet);
                bottomsumcol1.querySelector("ul").appendChild(licity);
                bottomsumcol1.querySelector("ul").appendChild(lizipCode);

                const lipickDate = document.createElement("li");
                lipickDate.innerText = pickUpDate;
                const lipickTime = document.createElement("li");
                lipickTime.innerText = pickUpTime;
                const lipickComment = document.createElement("li");
                lipickComment.innerText = pickUpComment;
                bottomsumcol2.querySelector("ul").appendChild(lipickDate);
                bottomsumcol2.querySelector("ul").appendChild(lipickTime);
                bottomsumcol2.querySelector("ul").appendChild(lipickComment);
            }
        }

        validation() {
            if (this.currentStep == 5) {
                this.addressValidation()
            }
            if (this.currentStep == 4) {
                this.institutionValidation();
            }
            if (this.currentStep==3){
                this.quantityValidation();
            }
            if (this.currentStep==2){
                this.categoryValidation();
            }
        }

        categoryValidation() {
            let categories = form.querySelectorAll("input[name='categories']");
            if (!this.check(categories)) {
                this.currentStep = 1;
                return false;
            }
        }

        institutionValidation() {
            let institution = form.querySelectorAll("input[name='institution']");
            if (!this.check(institution)) {
                this.currentStep = 3;
                return false;
            }
        }

        quantityValidation() {
            let quantity = form.querySelector("input#quantity");
            if (!quantity.value) {
                this.currentStep = 2;
                return false;
            }
        }

        addressValidation() {
            const stepInstructions= document.querySelector("[data-step='4'] h3");
            const h4=stepInstructions.querySelector("h4");
            if (h4){
                stepInstructions.removeChild(h4);
            }
            let street = form.querySelector("input#street").value;
            let city = form.querySelector("input#city").value;
            let zipCode = form.querySelector("input#zipCode").value;
            let pickUpTime = form.querySelector("input#pickUpTime").value;
            let pickUpDate = form.querySelector("input#pickUpDate").value;
            let invalidMessage = "";
            if (!street) {
                invalidMessage += "Nie podano ulicy odbioru\n";
            } else if (!city) {
                invalidMessage += "Nie podano miasta odbioru\n"
            }else if (!zipCode){
                invalidMessage+="Nie podano kodu pocztowego miasta odbioru\n";
            }else if (!pickUpTime){
                invalidMessage+="Nie podano godziny odbioru\n";
            }else if (!pickUpDate){
                invalidMessage+="Nie podano daty odbioru\n";
            }

            if (invalidMessage != "") {
                const message = document.createElement("h4");
                message.classList.add("error");
                message.innerText = invalidMessage;
                stepInstructions.appendChild(message);
                this.currentStep = 4;
                return false;
            }
        }

        check(elements) {
            let count = 0;
            for (const el of elements) {
                if (el.checked) {
                    count++;
                }
            }
            if (count === 0) {
                return false;
            } else return true;
        }


    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }


});
