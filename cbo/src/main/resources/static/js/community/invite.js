let selectedMembers = [];

function openInvitePopup() {
  window.open(`/community/${cId}/inviteList`, 'invitePopup', 'width=600,height=500');
}

function addSelectedMember(id, name, grade) {
  if (selectedMembers.some(m => m.id === id)) {
    alert('이미 선택된 멤버입니다.');
    return;
  }
  
  selectedMembers.push({ id, name, grade });
  renderSelectedMembers();
}

function renderSelectedMembers() {
  const wrapper = document.getElementById('selectedMembers');
  wrapper.innerHTML = '';
  
  selectedMembers.forEach(m => {
    const span = document.createElement('span');
    span.textContent = `${m.grade} ${m.name}`;
    
    const xBtn = document.createElement('button');
    xBtn.textContent = 'X';
    xBtn.onclick = () => {
      selectedMembers = selectedMembers.filter(mem => mem.id !== m.id);
      renderSelectedMembers();
    };
    
    span.appendChild(xBtn);
    wrapper.appendChild(span);
  });
}

function sendInvites() {
  if (selectedMembers.length === 0) {
    alert('초대할 멤버를 선택하세요.');
    return;
  }

  const ids = selectedMembers.map(m => m.id);
  sendRequest(
    `/community/${cId}/invite`,
    JSON.stringify({ memberIds: ids }),
    function() {
      if (XHR.readyState === 4 && XHR.status === 200) {
        const res = JSON.parse(XHR.responseText);
        alert(res.msg);
        if (res.status === 'success') {
          selectedMembers = [];
          renderSelectedMembers();
        }
      }
    },
    'POST'
  );
}
