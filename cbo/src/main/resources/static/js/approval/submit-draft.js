// Render writer's name and dept name
if (userInfo){
    document.querySelector('#writer').textContent = userInfo.name;
    document.querySelector('#dept').textContent = userInfo.dept_name;
}

// Render today's date
const today = new Date();
const year = today.getFullYear();
const month = String(today.getMonth() + 1).padStart(2, '0');
const day = String(today.getDate()).padStart(2, '0');
document.querySelector('#writeDate').textContent = `${year}-${month}-${day}`;


document.querySelector('form').addEventListener('submit', () => {
    const draft = document.querySelector('#draft');

    document.querySelector('input[type="hidden"][name="title"]').value = draft.querySelector('#title').value;

    // Make content of doc uneditable before submitting
    draft.querySelectorAll('input[type="text"]').forEach(input => {
        const span = document.createElement('span');

        span.textContent = input.value;
        input.replaceWith(span);
    });

    draft.querySelectorAll('input[type="checkbox"], input[type="radio"]').forEach(input => {
        const label = input.closest('label');
        const text = label ? label.innerText.trim() : input.value;

        if (input.checked) {
            const span = document.createElement('span');
            span.textContent = text;
            if (input.type == 'checkbox')
                span.style.marginRight = '2px';
            if (label) label.replaceWith(span);
            else input.replaceWith(span);
        } else {
            if (label) label.remove();
            else input.remove();
        }
    });

    draft.querySelectorAll('div[contenteditable="true"]').forEach(elem => elem.removeAttribute('contenteditable'));

    // Submit through hidden input
    const hiddenInput = document.querySelector('input[type="hidden"][name="content"]');
    hiddenInput.value = draft.innerHTML;
});


// Uncheck all checkboxes and fold accordion when modal is closed
const approvalLinesModal = document.querySelector('#approvalLinesModal');

approvalLinesModal.addEventListener('hide.bs.modal', function () {
    const accordionButtons = approvalLinesModal.querySelectorAll('button.accordion-button')

    accordionButtons.forEach(button => {
        button.classList.add('collapsed');
        button.setAttribute('aria-expanded', 'false');
        document.querySelector(button.dataset.bsTarget).classList.remove('show');
    });

    const checkboxes = approvalLinesModal.querySelectorAll('input[type="checkbox"][name="selectedIds"]');
    checkboxes.forEach(cb => cb.checked = false);

    if (approvalLinesModal.contains(document.activeElement)) {
        document.activeElement.blur();
    }
});


const passSelectedItems = function (list, hiddenInputs, inputName) {
    const selectedCheckboxes = approvalLinesModal.querySelectorAll('input[type="checkbox"][name="selectedIds"]:checked');

    // Clear list
    list.innerHTML = '';
    hiddenInputs.innerHTML = '';

    selectedCheckboxes.forEach(cb => {
        const label = document.querySelector(`label[for=${cb.id}]`);
        const nameAndGrade = label ? label.textContent : '알 수 없음';

        const accordionBody = cb.closest('div.accordion-body');
        const deptName = accordionBody?.dataset.dept || '알 수 없음'
        const img = accordionBody.querySelector(`img[alt="profile_image"]`);

        // Append list items
        const li = document.createElement('li');
        li.classList.add('list-group-item');
        if (img) {
            const clonedImg = img.cloneNode();
            li.appendChild(clonedImg);
        }
        const span = document.createElement('span');
        span.textContent = `${deptName} ${nameAndGrade}`;
        li.appendChild(span);
        list.appendChild(li);

        // Append hidden inputs
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = inputName;
        input.value = cb.value;
        hiddenInputs.appendChild(input);

        // Close modal
        bootstrap.Modal.getInstance(approvalLinesModal)?.hide();
    });
};

const approversList = document.querySelector('#approversList');
const approversInputs = document.querySelector('#approversInputs');
document.querySelector('#confirmApprovers')?.addEventListener('click', () => passSelectedItems(approversList, approversInputs, 'approverIds'));

const reviewersList = document.querySelector('#reviewersList');
const reviewersInputs = document.querySelector('#reviewersInputs');
document.querySelector('#confirmReviewers')?.addEventListener('click', () => passSelectedItems(reviewersList, reviewersInputs, 'reviewerIds'));