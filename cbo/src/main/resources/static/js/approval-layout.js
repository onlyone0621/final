  const formatModal = document.querySelector('#formatModal');
  formatModal.addEventListener('hidden.bs.modal', function () {
    const radios = formatModal.querySelectorAll('input[type="radio"][name="id"]');
    radios.forEach(radio => radio.checked = false);

    if (this.contains(document.activeElement)) {
        document.activeElement.blur();
    }
  });