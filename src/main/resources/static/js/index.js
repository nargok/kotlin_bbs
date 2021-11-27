function changeArticleSubmit(type) {
    let changeArticleForm = document.form_change_article
    let checks = document.getElementsByName("article_check")
    let articleId = null;

    for (let index=0; index < checks.length; index++) {
        if (checks[index].checked) {
            articleId = checks[index].getAttribute("data-id")
            break;
        }
    }

    if (articleId == null) {
        alert("記事を選択してください")
        return;
    }

    switch (type) {
        case "update":
            console.log('articleId inside update', articleId)
            changeArticleForm.action = "/edit/" + articleId
            changeArticleForm.submit()
            break;
        default:
            break;
    }
}