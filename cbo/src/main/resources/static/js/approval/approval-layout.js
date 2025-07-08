const formatModal = document.querySelector('#formatModal');
formatModal.addEventListener('hide.bs.modal', function () {
  const radios = formatModal.querySelectorAll('input[type="radio"][name="id"]');
  radios.forEach(radio => radio.checked = false);

  if (formatModal.contains(document.activeElement)) {
      document.activeElement.blur();
  }
});

