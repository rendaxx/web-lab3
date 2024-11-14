document.addEventListener("DOMContentLoaded", function () {
    if ($("#dont-touch ~ tr").length !== 0) {
        $("#table-footer-helper").remove();
    }
    const spinner = document.getElementById("j_idt6:radius-select_input");
    if (spinner) {
        drawArea(spinner);
    }
    updateGraph();
});

function drawArea(input) {
    $("#xy-plane").find(".added-from-js").remove();
    const value = input.value
    if (value !== "") {
        const rect = document.createElementNS('http://www.w3.org/2000/svg', 'polygon');
        rect.setAttribute('points', `150,150 150,${150 + 28 * value} ${150 + 28 * value},${150 + 28 * value} ${150 + 28 * value},150`);
        rect.setAttribute('fill', 'blueviolet');
        rect.setAttribute('opacity', '0.3');
        rect.setAttribute('class', 'added-from-js');

        const trg = document.createElementNS('http://www.w3.org/2000/svg', 'polygon');
        trg.setAttribute('points', `150,150 ${150 - 28 * value},150 150,${150 - 14 * value}`);
        trg.setAttribute('fill', 'blueviolet');
        trg.setAttribute('opacity', '0.3');
        trg.setAttribute('class', 'added-from-js');

        const circle = document.createElementNS('http://www.w3.org/2000/svg', 'path');
        circle.setAttribute('d', `M 150,${150 + 14 * value}
               A ${14 * value},${14 * value} 0,0,1 ${150 - 14 * value},150
               H 150
               V ${150 + 14 * value}`);
        circle.setAttribute('fill', 'blueviolet');
        circle.setAttribute('opacity', '0.3');
        circle.setAttribute('class', 'added-from-js');

        $('#xy-plane').append(rect, trg, circle);
    }
}

$("#xy-plane").on("click", function (e) {
    const pixX = e.pageX - $(this).offset().left;
    const pixY = e.pageY - $(this).offset().top;
    const x = (pixX - 300) / 56;
    const y = -(pixY - 300) / 56;

    const $form = $("form");
    $form.find(".error").remove();
    const r = $("#radius-select").val();
    if (r === "") {
        $form.find("select[name=r]").after('<div class="error">Выберите значение R</div>');
        return;
    }

    jsf.ajax.request()

    $.ajax({
        type: "GET",
        url: "/controller",
        data: { x, y, r },
        dataType: "html",
        success(responseData) {
            $("#dont-touch ~ tr").remove();
            $(".dots-from-js").remove();
            $("#data-table-body").append(responseData);
            updateGraph();
            $("#table-footer-helper").remove();
        },
        error(jqXHR, textStatus) {
            alert(`${textStatus}\n${jqXHR.statusText}`);
        }
    });
});

function updateGraph() {
    $("#dont-touch ~ tr").each(function () {
        const $cells = $(this).children();
        const isHit = $cells.last().text().trim() === "Да";
        const x = parseFloat($cells.eq(0).text());
        const y = parseFloat($cells.eq(1).text());

        const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        circle.setAttribute("cx", (x * 56 + 300) / 2);
        circle.setAttribute("cy", (-y * 56 + 300) / 2);
        circle.setAttribute("r", 2);
        circle.setAttribute("fill", isHit ? "green" : "red");
        circle.setAttribute("class", "dots-from-js");

        document.getElementById('xy-plane').appendChild(circle);
    });
}
