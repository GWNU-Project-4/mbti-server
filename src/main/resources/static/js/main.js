document.getElementById("recommend-button").addEventListener("click", async () => {
    const mbti = document.getElementById("mbti-input").value.trim();
    const recommendationsDiv = document.getElementById("recommendations");

    if (mbti) {
        // API 호출
        const response = await fetch(`http://localhost:8080/api/nlp/recommendations/${mbti}`);
        const recommendations = await response.json();

        // 추천 결과를 화면에 표시
        recommendationsDiv.innerHTML = ""; // 이전 추천 결과 삭제
        recommendations.forEach(rec => {
            const recElement = document.createElement("div");
            recElement.innerHTML = `<strong>${rec.type === 'car' ? '자동차' : '여행지'}:</strong> ${rec.name} - ${rec.description}`;
            recommendationsDiv.appendChild(recElement);
        });
    } else {
        recommendationsDiv.innerHTML = "<p>MBTI를 입력해 주세요.</p>";
    }
});
