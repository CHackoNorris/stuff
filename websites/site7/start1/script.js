gsap.config({trialWarn: false});
let select = s => document.querySelector(s),
		q = gsap.utils.selector(document),
		toArray = s => gsap.utils.toArray(s),
		mainSVG = select('#mainSVG'),
		allG1a = toArray('#g1a rect'),
		allG2a = toArray('#g2a rect'),
		allG1b = toArray('#g1b rect'),
		allG2b = toArray('#g2b rect'),
		allG1c = toArray('#g1c rect'),
		allG2c = toArray('#g2c rect'),
		allG1 = toArray('#g1 rect'),
		allG2 = toArray('#g2 rect')

gsap.set('svg', {
	visibility: 'visible'
})

const makeAnim = (arr1, arr2) => {
		let tl = gsap.timeline({
		repeat: -1,
		defaults: {
			ease: 'sine.inOut'
		}
	});
	tl.add('m1')
	.to(arr1, {
		attr:{
			height: 560
		},
		stagger: {
			each: 0.05
		}
	}, 'm1')
	.to(arr2, {
		attr:{
			height: 560,
			y: 20
		},
		stagger: {
			each: 0.05
		}
	}, 'm1')
	.add('m2', '-=0.18')
	.to(arr1.reverse(), {
		attr:{
			height: 60,
			y: 520
		},
		stagger: {
			each: 0.05
		}
	}, 'm2')
	.to(arr2.reverse(), {
		attr:{
			height: 60,
			y: 20
		},
		stagger: {
			each: 0.05
		}
	}, 'm2')
	.add('m3', '-=0.18')
	.to(arr1.reverse(), {
		attr:{
			//height: 60,
			x: '-=390',
		},
		stagger: {
			each: 0.05
		}
	}, 'm3')
.to(arr1, {
		attr:{
			width: '+=30'
		},
		stagger: {
			each: 0.05
		},
		//duration: 1,
		ease: 'slow(0.1, 0.8, true)'
	}, 'm3')
	.to(arr2.reverse(), {
		attr:{
			//height: 60,
			x: '+=390'
		},
		stagger: {
			each: 0.05
		}
	}, 'm3')
.to(arr2, {
		attr:{
			width: '+=30'
		},
		stagger: {
			each: 0.05
		},
		//duration: 1,
		ease: 'slow(0.1, 0.8, true)'
	}, 'm3')

	return tl;
}
let mainTl = gsap.timeline();

mainTl
.add(makeAnim(allG1a, allG2a), 0.045)
.add(makeAnim(allG1b, allG2b), 0.02)
.add(makeAnim(allG1c, allG2c), 0)