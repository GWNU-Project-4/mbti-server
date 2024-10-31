document.getElementById("recommendBtn").addEventListener("click", function() {
    const mbti = document.getElementById("mbti").value;
    const situation = document.getElementById("situation").value;
    const resultSection = document.getElementById("result");

    if (!mbti) {
        resultSection.innerHTML = "<p>MBTI를 입력해 주세요.</p>";
        return;
    }

    fetch('/api/recommendation/get', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ mbti, situation })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 응답에 문제가 있습니다.');
            }
            return response.json(); // 응답을 JSON으로 직접 받아옴
        })
        .then(jsonData => {
            console.log("서버 응답 데이터:", jsonData); // 디버깅용 로그
            // 서버로부터 받은 recommendation을 resultSection에 표시
            resultSection.innerHTML = `<h2>추천 결과</h2><p>${jsonData.recommendation.replace(/\n/g, "<br>")}</p>`;
        })
        .catch(error => {
            resultSection.innerHTML = "<p>추천을 불러오는 중 문제가 발생했습니다. 다시 시도해 주세요.</p>";
            console.error("Error:", error);
        });
});
