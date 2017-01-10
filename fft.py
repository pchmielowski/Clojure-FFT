import numpy as np

def create_test(x):
    print
    print '(is(cmp'
    print('(fft [' + ' '.join('{}'.format(k) for k in x) + '])')
    y = np.fft.fft(np.array(x))
    print('[' + '\n'.join('( complex {r} {i})'.format(r=k.real, i=k.imag) for k in y) + ']))')

create_test([1])
create_test([0])

create_test([1, 0])
create_test([0, 1])

create_test([0, 1, 2, 3])
create_test([0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3])
create_test([-1, -100, 100, 1000])
