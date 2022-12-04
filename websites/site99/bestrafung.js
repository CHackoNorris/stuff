const observer = new IntersectionObserver((nodes) => {
    nodes.forEach((node) =>
        document.body.classList.toggle("footer-pull", node.isIntersecting)
    );
});

observer.observe(document.querySelector("#footer"));