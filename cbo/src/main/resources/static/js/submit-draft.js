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