const memberListModal = document.querySelector('#memberListModal');
memberListModal.addEventListener('hide.bs.modal', function () {
  const checkboxes = memberListModal.querySelectorAll('input[type="checkbox"][name="receiverIds"]');
  checkboxes.forEach(cb => cb.checked = false);

  if (memberListModal.contains(document.activeElement)) {
      document.activeElement.blur();
  }
});
