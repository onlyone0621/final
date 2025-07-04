const memberListModal = document.querySelector('#memberListModal');
memberListModal.addEventListener('hide.bs.modal', function () {
    const accordionButtons = memberListModal.querySelectorAll('button.accordion-button')

    accordionButtons.forEach(button => {
        button.classList.add('collapsed');
        button.setAttribute('aria-expanded', 'false');
        document.querySelector(button.dataset.bsTarget).classList.remove('show');
    });

    const checkboxes = memberListModal.querySelectorAll('input[type="checkbox"][name="receiverIds"]');
    checkboxes.forEach(cb => cb.checked = false);

    if (memberListModal.contains(document.activeElement)) {
        document.activeElement.blur();
    }
});


const receiversList = document.querySelector('#receiversList');
document.querySelector('#confirmReceivers').addEventListener('click', function () {
    const selectedCheckboxes = memberListModal.querySelectorAll('input[type="checkbox"][name="receiverIds"]:checked');
    
    const receiversList = document.querySelector('#receiversList');
    const hiddenInputs = document.querySelector('#hiddenInputs')

    // Clear list
    receiversList.innerHTML = '';
    hiddenInputs.innerHTML = '';

    selectedCheckboxes.forEach(cb => {
        const label = document.querySelector(`label[for=${cb.id}]`);
        const nameAndGrade = label ? label.textContent : '알 수 없음';
        const deptName = cb.closest('div[class="accordion-body"]').dataset.dept || '알 수 없음'

        // Append list items
        const li = document.createElement('li');
        li.classList.add('list-group-item');
        li.textContent = `${deptName} ${nameAndGrade}`;
        receiversList.appendChild(li);

        // Append hidden inputs
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'receiverIds';
        input.value = cb.value;
        hiddenInputs.appendChild(input);

        // Close modal
        bootstrap.Modal.getInstance(memberListModal)?.hide();
    })
});
