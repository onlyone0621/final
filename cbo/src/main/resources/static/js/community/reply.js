/////XMLHttpRequest 기반, 모달 메시지 포함, 실시간 목록 갱신, 깔끔한 구조
// writeReply();      // 댓글 작성
// addReply();        // 답글 작성
// editReply();       // 수정
// deleteReply();     // 삭제
// replyLists();      // 목록 출력


document.addEventListener('DOMContentLoaded', () => {
  const section = document.getElementById('replySection');
  const cId = section.dataset.cid;
  const boardId = section.dataset.boardid;
  const postId = section.dataset.postid;
  const loggedInUserId = section.dataset.userid;

  window.writeReply = function () {
    const content = document.getElementById('replyContent').value.trim();
    if (!content) return alert("내용을 입력하세요.");

    fetch(`/community/${cId}/board/${boardId}/post/${postId}/reply`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json; charset=utf-8' },
      body: JSON.stringify({ content })
    }).then(res => res.json()).then(data => {
      if (data.status === 'success') {
        document.getElementById('replyContent').value = '';
        loadReplies();
      } else {
        alert(data.msg);
      }
    });
  };

  window.loadReplies = function () {
    fetch(`/community/${cId}/board/${boardId}/post/${postId}/replyList`)
      .then(res => res.json())
      .then(list => {
        const html = list.map(r => {
          const date = new Date(r.WRITE_DATE).toLocaleString('ko-KR', {
            year: 'numeric', month: '2-digit', day: '2-digit',
            hour: '2-digit', minute: '2-digit'
          }).replace(/\./g, '.').replace(',', '');

          return `
            <div id="reply-${r.ID}" class="reply-item" data-memberid="${r.MEMBER_ID}">
              <div class="reply-header">
                ${r.MEMBER_NAME} <span class="reply-date">(${date})</span>
              </div>
              <div class="reply-content" id="content-${r.ID}">${r.CONTENT}</div>
              <div class="reply-buttons">
                ${r.MEMBER_ID == loggedInUserId ? `
                  <button onclick="startEdit(${r.ID}, '${r.CONTENT.replace(/'/g, "\\'")}')">수정</button>
                  <button onclick="deleteReply(${r.ID})">삭제</button>
                ` : ''}
              </div>
            </div>
          `;
        }).join('');
        document.getElementById('replyList').innerHTML = html;
      });
  };

  window.startEdit = function (id, oldContent) {
    const reply = document.getElementById(`reply-${id}`);
    reply.classList.add('reply-editing');

    document.getElementById(`content-${id}`).innerHTML =
      `<textarea id="edit-${id}" style="width:100%;">${oldContent}</textarea>`;

    const btnGroup = reply.querySelector('.reply-buttons');
    btnGroup.innerHTML = `
      <button class="save-btn" onclick="saveEdit(${id})">저장</button>
      <button class="cancel-btn" onclick="cancelEdit(${id}, '${oldContent.replace(/'/g, "\\'")}')">취소</button>
    `;
  };

  window.saveEdit = function (id) {
    const newContent = document.getElementById(`edit-${id}`).value.trim();
    if (!newContent) return alert("내용을 입력하세요.");

    fetch(`/community/${cId}/board/${boardId}/post/${postId}/reply/${id}/edit`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json; charset=utf-8' },
      body: JSON.stringify({ content: newContent })
    }).then(res => res.json()).then(data => {
      if (data.status === 'success') {
        loadReplies();
      } else {
        alert(data.msg);
      }
    });
  };

  window.likePost = function () {
    const section = document.getElementById('replySection');
    const cId = section.dataset.cid;
    const boardId = section.dataset.boardid;
    const postId = section.dataset.postid;

    fetch(`/community/${cId}/board/${boardId}/post/${postId}/like`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({})
    })
      .then(res => res.json())
      .then(data => {
        if (data.status === 'success') {
          document.getElementById('upvoteCount').innerText = data.upvote;
        } else {
          alert(data.msg || '좋아요 실패');
        }
      })
      .catch(err => {
        console.error('좋아요 에러:', err);
      });
  };
  
  
  
  
  window.cancelEdit = function (id, content) {
    loadReplies(); // 간단히 전체 다시 불러오는 방식으로 취소
  };

  window.deleteReply = function (id) {
    if (!confirm("댓글을 삭제하시겠습니까?")) return;

    fetch(`/community/${cId}/board/${boardId}/post/${postId}/reply/${id}/delete`, {
      method: 'POST'
    }).then(res => res.json()).then(data => {
      if (data.status === 'success') {
        loadReplies();
      } else {
        alert(data.msg);
      }
    });
  };

  loadReplies();
});
