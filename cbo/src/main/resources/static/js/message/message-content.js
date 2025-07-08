document.querySelector('#deleteMessage')?.addEventListener('click', function () {
  fetch('deleteMessages', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify([messageId])
  })
  .then(res => {
    if (!res.ok) throw new Error('실패');
    return res.text(); 
  })
  .then(text => {
    const result = parseInt(text);// now it's safe to parse
    const msg = result === 1 ? '삭제 성공' : '삭제 실패';
    window.alert(msg);
    location.href = 'receivedMessages';
  })
  .catch(err => alert(err.message));
});
