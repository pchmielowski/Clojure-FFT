import numpy as np

def f (x): print np.fft.fft(np.array(x))

f([1])
f([0])

f([1, 0])
f([0, 1])

f([0, 1, 2, 3])
