const checkAllcb = document.querySelector('#checkAll');

checkAllcb.addEventListener('change', function() {
    const checkboxes = document.querySelectorAll('input[type="checkbox"][name="selectedIds"]');
    if (checkboxes)
        checkboxes.forEach(cb => cb.checked = this.checked);
});

const processSelectedItems = function(curPage, action, pageName) {
    const selectedIds = [...document.querySelectorAll('input[type="checkbox"][name="selectedIds"]:checked')]
        .map(cb => cb.value);

    if (!selectedIds || selectedIds.length === 0){
        window.alert('항목을 선택하세요');
        return;
    }

    fetch(`/${action}`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(selectedIds)
    })
    .then(res => {
        const result = parseInt(res.text());
        if (!res.ok || result < 1) throw new Error('실패');
        
        return fetch(`/${pageName}?curPage=${curPage}`, {
            headers: {'X-Requested-With': 'XMLHttpRequest'}
        });
    })
    .then(res => res.text())
    .then(renderedHtml => {
        document.querySelector('#tableBody').innerHTML = renderedHtml;
        history.pushState(null, '', `/${pageName}?curPage=${curPage}`);
    })
    .catch(err => alert(err.message));
};

const markAsReadBtn = document.querySelector('#markAsRead');
const deleteMessages = document.querySelector('#deleteMessages');

markAsReadBtn?.addEventListener('click', () => processSelectedItems(curPage, 'markAsRead', pageName));
deleteMessages?.addEventListener('click', () => processSelectedItems(curPage, 'deleteMessages', pageName));
