package com.example.cactoos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.cactoos.Scalar;
import org.cactoos.scalar.IoCheckedScalar;
import org.cactoos.scalar.StickyScalar;
import org.cactoos.scalar.SyncScalar;

class EncryptedX implements Encrypted {

	private final IoCheckedScalar<String> text;

	public EncryptedX(String text) {
		this(() -> text);
	}

	public EncryptedX(InputStream stream) {
		this(() -> {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (true) {
				int one = stream.read();
				if (one < 0) {
					break;
				}
				baos.write(one);
			}
			return new String(baos.toByteArray());

		});
	}

	public EncryptedX(Scalar<String> source) {
		this.text = new IoCheckedScalar<>(
				         new SyncScalar<>(
					         new StickyScalar<>(source)
					     )
			        );
	}

	@Override
	public String asString() throws IOException {
		final byte[] bt = text.value().getBytes();
		final byte[] out = new byte[bt.length];
		for (int i = 0; i < bt.length; i++) {
			out[i] = (byte) (bt[i] + 1);
		}
		return new String(out);
	}

}