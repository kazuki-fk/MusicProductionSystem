gsap.registerPlugin(ScrollTrigger);

window.addEventListener("load", () => {
    ScrollTrigger.refresh();
});

// 1. ヒーローテキストのフェードアウト
gsap.to(".split-text", {
    scrollTrigger: {
        trigger: ".hero",
        start: "top top",
        end: "bottom top",
        scrub: true
    },
    opacity: 0,
    y: -50,
    ease: "none"
});

// 2. ワークス・スタッキング
const cards = gsap.utils.toArray(".work-card-item");
cards.forEach((card, i) => {
    ScrollTrigger.create({
        trigger: card,
        start: "top 10%", 
        endTrigger: ".works-stack",
        end: "bottom bottom",
        pin: true,
        pinSpacing: false,
        scrub: true,
        onUpdate: (self) => {
            if (i < cards.length - 1) {
                const prog = self.progress;
                gsap.set(card, {
                    scale: 1 - (prog * 0.05),
                    opacity: 1 - (prog * 0.6),
                    filter: `blur(${prog * 10}px)`
                });
            }
        }
    });
});

// 3. マウス・背景連動
const follower = document.querySelector(".cursor-follower");
window.addEventListener("mousemove", (e) => {
    if(follower) {
        gsap.to(follower, { x: e.clientX, y: e.clientY, duration: 0.5 });
    }

    const xMove = (e.clientX / window.innerWidth - 0.5) * 30;
    const yMove = (e.clientY / window.innerHeight - 0.5) * 30;
    gsap.to(".bg-blur", { x: xMove, y: yMove, duration: 2 });
});

// --- 追加：要素のフェードイン演出 ---
gsap.utils.toArray(".reveal").forEach((el, i) => {
    gsap.from(el, {
        scrollTrigger: {
            trigger: el,
            start: "top 90%",
            toggleActions: "play none none none"
        },
        opacity: 0,
        y: 30,
        duration: 1,
        delay: i * 0.1,
        ease: "power2.out"
    });
});